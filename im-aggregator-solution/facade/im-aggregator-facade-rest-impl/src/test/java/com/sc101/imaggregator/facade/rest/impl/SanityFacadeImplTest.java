package com.sc101.imaggregator.facade.rest.impl;

import com.google.gson.Gson;
import com.sc101.imaggregator.common.config.CommonAppConfig;
import com.sc101.imaggregator.facade.rest.api.dto.SanityTestDTO;
import com.sc101.imaggregator.service.api.SanityService;
import com.sc101.imaggregator.service.model.SanityTest;
import com.sc101.imaggregator.facade.rest.impl.SanityFacadeImpl;
import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Amit Tal
 * @since 1/8/14
 *
 * SanityTest REST facade tests, backed by spring MockMvc
 */
public class SanityFacadeImplTest {

    private Logger logger = LoggerFactory.getLogger(SanityFacadeImplTest.class);

    @InjectMocks
    private SanityFacadeImpl sanityFacadeImpl;

    @Mock
    private SanityService sanityService;

    @Mock
    private Mapper mapper;

    private MockMvc mockMvc;
    private Gson gson;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(sanityFacadeImpl).build();
        gson = new Gson();
    }

    @Test
    public void testCreateSanityTest() throws Exception {
        SanityTestDTO sanityTestDTO = generateSanityTest();
        String sanityTestDtoJson = gson.toJson(sanityTestDTO);
        mockMvc.perform(post(SanityFacadeImpl.RESOURCE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(sanityTestDtoJson))
                .andDo(print())
                .andExpect(status().isCreated());
        Mockito.verify(sanityService).createSanityTest(Matchers.any(SanityTest.class));
    }

    @Test
    public void testCreateSanityTestNullRequester() throws Exception {
        SanityTestDTO sanityTestDTO = generateSanityTest();
        sanityTestDTO.setRequesterEmail(null);
        String sanityTestDtoJson = gson.toJson(sanityTestDTO);
        mockMvc.perform(post(SanityFacadeImpl.RESOURCE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(sanityTestDtoJson))
                .andDo(print())
                .andExpect(status().isBadRequest());
        Mockito.verifyZeroInteractions(sanityService);
    }

    @Test
    public void testReadSanityTest() throws Exception {
        String id = "stam-id";
        mockMvc.perform(get(SanityFacadeImpl.RESOURCE + "/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().string(""))
                .andExpect(status().isOk());
        Mockito.verify(sanityService).readSanityTest(Matchers.eq(id));
    }

    private SanityTestDTO generateSanityTest() {
        SanityTestDTO sanityTestDTO = new SanityTestDTO();
        sanityTestDTO.setRequesterEmail("sanity@test.com");
        return sanityTestDTO;
    }
}
