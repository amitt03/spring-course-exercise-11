package com.sc101.imaggregator.facade.rest.impl;

import com.google.gson.Gson;
import com.sc101.imaggregator.common.config.CommonAppConfig;
import com.sc101.imaggregator.facade.rest.api.dto.ImMessageDTO;
import com.sc101.imaggregator.facade.rest.impl.config.FacadeConfig;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Amit Tal
 * @since 5/8/2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {CommonAppConfig.class})
public class ImAggregatorFacadeImplTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    private Gson gson;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        this.gson = new Gson();
    }

    @Test
    public void testSendSMS() throws Exception {
        ImMessageDTO imMessageDTO = generateImMesageDTO(501);
        String json = gson.toJson(imMessageDTO);
        this.mockMvc.perform(post("/im").
                content(json).
                accept(MediaType.APPLICATION_JSON).
                contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isCreated()).
                andDo(print());
    }

    @Test
    public void testSendSMSBidInsufficient() throws Exception {
        ImMessageDTO imMessageDTO = generateImMesageDTO(1);
        String json = gson.toJson(imMessageDTO);
        this.mockMvc.perform(post("/im").
                content(json).
                accept(MediaType.APPLICATION_JSON).
                contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isBadRequest()).
                andDo(print());
    }

    @Test
    public void testSendSMSIllegalFromNumber() throws Exception {
        ImMessageDTO imMessageDTO = generateImMesageDTO(501);
        imMessageDTO.setFromNumber("abcdefghij");
        String json = gson.toJson(imMessageDTO);
        this.mockMvc.perform(post("/im").
                content(json).
                accept(MediaType.APPLICATION_JSON).
                contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isBadRequest()).
                andDo(print());
    }

    @Test
    public void testSendSMSIllegalBid() throws Exception {
        ImMessageDTO imMessageDTO = generateImMesageDTO(-1);
        String json = gson.toJson(imMessageDTO);
        this.mockMvc.perform(post("/im").
                content(json).
                accept(MediaType.APPLICATION_JSON).
                contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isBadRequest()).
                andDo(print());
    }

    private ImMessageDTO generateImMesageDTO(int bidInCents) {
        ImMessageDTO imMessageDTO = new ImMessageDTO();
        imMessageDTO.setUserId("1");
        imMessageDTO.setFromNumber("0555555555");
        imMessageDTO.setToNumber("0544444444");
        imMessageDTO.setData("Hello World");
        imMessageDTO.setBidInCent(bidInCents);
        return imMessageDTO;
    }
}
