package com.anuwat.leankafka.consumer;



import java.util.ArrayList;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class ConsumerKafka {
	
public static void main(String[] args) {
		
		Properties properties=new Properties();
		properties.put("bootstrap.servers", "localhost:9092");
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("group.id","test1");
		
		KafkaConsumer< String, String> consumer=new KafkaConsumer<String, String>(properties);
		
		
		ArrayList<String> topics=new ArrayList<String>();
		topics.add("java-topic");
		
		consumer.subscribe(topics); // You can subscribe to any number of topics.
		
		try {
			
			while(true){
				
				ConsumerRecords<String, String> records = consumer.poll(1000);
				
				for(ConsumerRecord<String, String> record : records){
					record.value();
					System.out.println("Record read in KafkaConsumerApp : " +  record.toString());
					
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Inside exception loop : ");
			e.printStackTrace();
		}finally{
			consumer.close();
		}
	}
}