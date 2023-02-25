package spring.mvc.basic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.mvc.ModelData;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-json-v1")
    // Servlet 으로 처리
    public void requestJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ServletInputStream inputStream = request.getInputStream();
        String body = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        ModelData data = objectMapper.readValue(body, ModelData.class);
        log.info("username={}, age={}", data.getUserName(), data.getAge());

        response.getWriter().write("Request JSON");

    }

    @ResponseBody
    @PostMapping("/request-json-v2")
    // String 으로 처리
    public String requestJsonV2(@RequestBody String body) throws JsonProcessingException {

        ModelData data = objectMapper.readValue(body, ModelData.class);
        log.info("username={}, age={}", data.getUserName(), data.getAge());

        return "Request JSON";

    }

    @ResponseBody
    @PostMapping("/request-json-v3")
    // 객체로 처리
    public String requestJsonV3(@RequestBody ModelData data) throws JsonProcessingException {

        log.info("username={}, age={}", data.getUserName(), data.getAge());

        return "Request JSON";

    }

    @ResponseBody
    @PostMapping("/request-json-v4")
    // HttpEntity 로 처리 (Response String)
    public String requestJsonV4(HttpEntity<ModelData> httpEntity) throws JsonProcessingException {

        ModelData data = httpEntity.getBody();
        log.info("username={}, age={}", data.getUserName(), data.getAge());

        return "Request JSON";

    }

    @ResponseBody
    @PostMapping("/request-json-v5")
    // HttpEntity 로 처리 (Response 객체)
//    public ModelData requestJsonV5(HttpEntity<ModelData> httpEntity) throws JsonProcessingException {
        public ModelData requestJsonV5(@RequestBody ModelData data) {
        log.info("username={}, age={}", data.getUserName(), data.getAge());

        return data;

    }
}
