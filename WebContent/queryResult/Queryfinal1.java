package query;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;

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
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class Queryfinal1 {
	
	public static class Map extends Mapper<LongWritable, Text, Text, Text> {
		
		private static HashMap<String, String> map = new HashMap<String, String>();
		private BufferedReader buff;
		
		//first we load keywords into hash map
		public void setup(Context context) throws IOException, InterruptedException {
			Path[] cacheFilesLocal = DistributedCache.getLocalCacheFiles(context.getConfiguration());
			for (Path eachPath: cacheFilesLocal) {
				if (eachPath.getName().toString().trim().equals("KeyWords-2")) {
					loadMyPageHashMap(eachPath, context);
				}
			}
		}
		
		public void loadMyPageHashMap(Path filePath, Context context) throws IOException {
			String strLineRead = "";
			try {
				buff = new BufferedReader(new FileReader(filePath.toString()));
				while((strLineRead = buff.readLine()) != null) {
					String[] tokens = strLineRead.split(",");
					map.put(tokens[0], tokens[1]);
				}
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			} finally {
				if (buff != null) {
					buff.close();
				}
			}
		}
		
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			String[] tokens = value.toString().split(",");
			String category = map.get(tokens[1]);
			context.write(new Text(tokens[0]), new Text(category+","+tokens[3]));
		}
	}
	
	public static class Reduce extends Reducer<Text, Text, Text, Text> {

		
		public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
			float restaurant_score = 0;
			float lodging_score =0;
			float points_score = 0;
			for (Text value: values) {
				String[] tokens = value.toString().split(",");
				if (tokens[0].equals("restaurant")) {
					restaurant_score+= Float.parseFloat(tokens[1]);
				} else if (tokens[0].equals("hotel")) {
					lodging_score += Float.parseFloat(tokens[1]);
				} else {
					points_score += Float.parseFloat(tokens[1]);
				}
			}
			context.write(key, new Text(Float.toString(restaurant_score) +","+ Float.toString(lodging_score)+ ","+ Float.toString(points_score)));
		}
	}
	
	public static class Driver extends Configured implements Tool {
		@Override
		public int run(String[] args) throws Exception {
			Job job = new Job(getConf());
			Configuration conf = job.getConfiguration();
			job.setJobName("Queryfinal1");
			DistributedCache.addCacheFile(new URI("/home/KeyWords-2"), conf); //broadcast keywords
			job.setJarByClass(Driver.class);
			job.setNumReduceTasks(1);
			FileInputFormat.setInputPaths(job, new Path(args[0]));
			FileOutputFormat.setOutputPath(job, new Path(args[1]));
			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(Text.class);
			job.setMapperClass(Map.class);
			job.setReducerClass(Reduce.class);
			boolean success = job.waitForCompletion(true);
			return success? 0 : 1;
		}}
		
		public static void main(String[] args) throws Exception{
			int exitCode = ToolRunner.run(new Configuration(), new Driver(), args);
			System.exit(exitCode);
		}
		
	}

