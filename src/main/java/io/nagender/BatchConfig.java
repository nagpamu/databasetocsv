package io.nagender;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.nagender.config.Student;
import io.nagender.config.StudentRowMapper;
import io.nagender.processor.StudentProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	@Autowired
	private JobBuilderFactory jobbuilderfactory;
	@Autowired
	private StepBuilderFactory stepbuilderfactory;
	@Autowired
	private DataSource datasource ;
	
	@Bean
	public JdbcCursorItemReader<Student> reader(){
		JdbcCursorItemReader<Student> cursorItemReader = new JdbcCursorItemReader<Student>();
		 cursorItemReader.setDataSource(datasource);
		 cursorItemReader.setSql("SELECT studentid,FirstName,LastName,Branch,Email,PhoneNumber FROM student");
		 cursorItemReader.setRowMapper(new StudentRowMapper());
		 return cursorItemReader;
	}
	@Bean
	public StudentProcessor processor() {
       	return new StudentProcessor();	
	}
	
	public FlatFileItemWriter<Student> writer(){
		 FlatFileItemWriter<Student> writer= new FlatFileItemWriter<Student>();
		 writer.setResource(new ClassPathResource("student.csv"));
		 
		 DelimitedLineAggregator<Student> lineAggregator = new DelimitedLineAggregator<Student>();
		 lineAggregator.setDelimiter(",");
		 
		 BeanWrapperFieldExtractor<Student> fieldextractor=new BeanWrapperFieldExtractor<Student>();
		 fieldextractor.setNames(new String[]{"studentid","FirstName","LastName","Branch","Email","PhoneNumber"});
		 
		 lineAggregator.setFieldExtractor(fieldextractor);
		 
		 writer.setLineAggregator(lineAggregator);
		 
		 return writer;
		 
		 
		 
	}
	@Bean
	public Step step1() {
		
	return stepbuilderfactory.get("step1").<Student,Student>chunk(100).reader(reader()).processor(processor()).writer(writer()).build();
		
		
	}
	
	@Bean
	public Job exportStudentJob() {
		return jobbuilderfactory.get("exportStudentjob").incrementer(new RunIdIncrementer()).flow(step1()).end().build();
	}

}
