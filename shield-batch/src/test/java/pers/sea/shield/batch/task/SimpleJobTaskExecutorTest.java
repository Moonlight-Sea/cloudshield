package pers.sea.shield.batch.task;

import cn.hutool.core.io.FileUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.TestPropertySource;
import pers.sea.shield.batch.common.constant.BatchProperty;
import pers.sea.shield.batch.pojo.entity.Job;
import pers.sea.shield.batch.pojo.entity.JobConfig;
import pers.sea.shield.batch.service.IJobConfigService;
import pers.sea.shield.batch.service.IJobService;
import pers.sea.shield.batch.service.impl.JobConfigServiceImpl;
import pers.sea.shield.batch.service.impl.JobServiceImpl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

/**
 * @author moon on 12/1/2023
 */
@TestPropertySource(locations = "classpath:application-test.properties")
class SimpleJobTaskExecutorTest {

    @Mock
    private IJobService jobService;
    @Mock
    private IJobConfigService jobConfigService;

    @InjectMocks
    private SimpleJobTaskExecutor simpleJobTaskExecutor;

    private ObjectMapper objectMapper;
    private Job job;
    private JobConfig jobConfig;

    @BeforeEach
    public void setup() throws IOException {
        jobService = Mockito.mock(JobServiceImpl.class);
        jobConfigService = Mockito.mock(JobConfigServiceImpl.class);


        objectMapper = new ObjectMapper();
        // other serializer and deSerializer config ...

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        objectMapper.setDateFormat(dateFormat);

        JavaTimeModule javaTimeModule = new JavaTimeModule();

        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        objectMapper.registerModule(javaTimeModule);

        BatchProperty.monitorDir = "D:/Users/moon/Data/";
        BatchProperty.resultDir = "D:/Users/moon/Data/file/";
        BatchProperty.fileFlagSuffix = ".flag";

        jobConfig = objectMapper.readValue(FileUtil.file("./data/JobConfig.json"), JobConfig.class);
        given(jobConfigService.getById(0)).willReturn(jobConfig);

        simpleJobTaskExecutor = new SimpleJobTaskExecutor(jobService, jobConfigService, objectMapper);

        File file = FileUtil.file("./data/Job.json");
        job = objectMapper.readValue(file, Job.class);

    }

    @Test
    public void execute_test() throws IOException {
        // given - precondition or setup
        given(jobService.findWaitJob(anyString(), anyString())).willReturn(null);
        given(jobService.updateById(job)).willReturn(true);

        simpleJobTaskExecutor.setUp(job);

        simpleJobTaskExecutor.execute(job);
        // then - verify the results
    }
}