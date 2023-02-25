package spring.mvc.basic;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyController {
    @PostMapping("/request-body-v1")
    public void requestBodyV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String body = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", body);

        response.getWriter().write("Request Body");
    }

    @PostMapping("/request-body-v2")
    public void requestBodyV2(InputStream inputStream, Writer outputStream) throws IOException {

        String body = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", body);

        outputStream.write("Request Body");
    }

    @PostMapping("/request-body-v3")
    public HttpEntity<String> requestBodyV3(HttpEntity<String> httpEntity) throws IOException {

        String body = httpEntity.getBody();
        log.info("messageBody={}", body);

        return new HttpEntity<>("Request Body");
    }

    @ResponseBody
    @PostMapping("/request-body-v4")
    public String requestBodyV4(@RequestBody String body) throws IOException {
        log.info("messageBody={}", body);

        return "Request Body";
    }
}
