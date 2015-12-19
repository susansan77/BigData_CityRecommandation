package query;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.io.InterruptedIOException;
import java.util.Set;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class Queryfinal4 {

	private static int i;
	private static int j;
	private static int k;
	public static class Map extends Mapper<LongWritable, Text, Text, Text> {


	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			Configuration conf= context.getConfiguration();			
			float ii = Float.parseFloat(conf.get("p1"));
			float jj = Float.parseFloat(conf.get("p2"));
			float kk = Float.parseFloat(conf.get("p3"));
			String[] tokens = value.toString().split("\t");
			String[] categ = tokens[1].split(",");

			float total_score= Float.parseFloat(categ[0])*ii + Float.parseFloat(categ[1])*jj + Float.parseFloat(categ[2])*kk;
		context.write(new Text(tokens[0]), new Text(Float.toString(total_score)));
		}

	}
	public static class Reduce extends Reducer<Text, Text, Text, Text> {
		private HashMap<String, Float> map;
		public void setup(Context context) throws IOException, InterruptedException{
			map = new HashMap<String, Float>();
		}
		
		public void reduce(Text key, Iterable<Text> values, Context context) {
			for (Text value: values) {
				map.put(key.toString(), Float.parseFloat(value.toString()));
			}		
		}
		
		public void cleanup(Context context) throws IOException, InterruptedException {
			float max = Float.MIN_VALUE;
			for (float fl: map.values()) {
				if (fl > max) {
					max = fl;
				}
			}
			ArrayList<Entry<String, Float>> list = sortValue(map);
			for (Entry<String, Float> entry: list) {
				float score = entry.getValue()*100/max;
				context.write(new Text(entry.getKey()), new Text(Float.toString(score)));
			}
		}
			
		public ArrayList<Entry<String, Float>> sortValue(HashMap<String, Float> map) {
				ArrayList<Entry<String,Float>> list = new ArrayList<Entry<String,Float>>(map.entrySet());
				Collections.sort(list, new Comparator<Entry<String, Float>>(){

					@Override
					public int compare(Entry<String, Float> o1,
							Entry<String, Float> o2) {
						// TODO Auto-generated method stub
						return (int)(o2.getValue() - o1.getValue());
					}});
				return list;
			}
		}
		
		public static void main(String[] args) throws Exception{
			for (i=1; i<4; i++){
				for (j=1; j<4; j++){
					for (k=1; k<4; k++){
						Configuration conf = new Configuration();
						conf.set("p1",String.valueOf(i));
						conf.set("p2",String.valueOf(j));
						conf.set("p3",String.valueOf(k));
						Job job = new Job(conf, "Queryfinal");
						job.setJobName("Queryfinal");
						job.setJarByClass(Queryfinal4.class);
						job.setMapperClass(Map.class);
						job.setReducerClass(Reduce.class);
						job.setNumReduceTasks(1);
						job.setInputFormatClass(TextInputFormat.class);
						job.setOutputFormatClass(TextOutputFormat.class);
						job.setOutputKeyClass(Text.class);
						job.setOutputValueClass(Text.class);
						FileInputFormat.addInputPath(job, new Path(args[0]));			
						FileOutputFormat.setOutputPath(job, new Path("/home/586/finaloutput"
								+ String.valueOf(i)+String.valueOf(j)+String.valueOf(k)));
						job.waitForCompletion(true);
					}
				}
			}
		

		}	
	

}

