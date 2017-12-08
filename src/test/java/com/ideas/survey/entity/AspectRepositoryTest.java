package com.ideas.survey.entity;


import com.ideas.survey.config.EmpSurveyApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import sun.java2d.pipe.AAShapePipe;

import static org.junit.Assert.assertEquals;

/**
 * Created by idnvlr on 10/4/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmpSurveyApplication.class)
public class AspectRepositoryTest {
    @Autowired
    private AspectRepository aspectRepository;
    private  Aspects aspects = new Aspects("TestAspect");
    @Before
    public void setup()
    {
        aspectRepository.save(aspects);
    }

    @After
    public  void after(){
        aspectRepository.delete(aspects);
    }
    @Test
    public void shouldFindAspectByName(){
       assertEquals(aspects.getAspectId(),aspectRepository.findByAspectName("TestAspect").getAspectId());
    }

}