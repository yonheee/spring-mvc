package spring.mvc.basic;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.mvc.ModelData;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("userName");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("usename={}, age={}", name, age);

        response.getWriter().write("Query Param");
    }

    @ResponseBody
    @RequestMapping("/request-mapping-v2")
    public String requestParamV2(
            @RequestParam("userName") String name,
            @RequestParam int age
    ) {
        log.info("name = {}, age = {}", name, age);
        return "Request Param";
    }

    @ResponseBody
    @RequestMapping("/request-mapping-v3")
    public String requestParamV3(String userName, int age) {
        log.info("name = {}, age = {}", userName, age);
        return "Request Param";
    }

    @ResponseBody
    @RequestMapping("/request-mapping-v4")
    public String requestParamV4(
            @RequestParam(required = true) String userName,
            @RequestParam(required = true) int age
    ) {
        log.info("name = {}, age = {}", userName, age);
        return "Request Param";
    }

    @ResponseBody
    @RequestMapping("/request-mapping-v5")
    public String requestParamV5(
            @RequestParam(required = true, defaultValue = "yonhee") String userName,
            @RequestParam(required = true, defaultValue = "20") int age
    ) {
        log.info("name = {}, age = {}", userName, age);
        return "Request Param";
    }

    @ResponseBody
    @RequestMapping("/request-mapping-v6")
    public String requestParamV6(@RequestParam Map<String, Object> paraMap) {
        log.info("name = {}, age = {}", paraMap.get("userName"), paraMap.get("age"));
        return "Request Param";
    }

    @ResponseBody
    @RequestMapping("/model-attr-v1")
    public String modelAttributeV1(
            @RequestParam String userName,
            @RequestParam int age
    ) {
        ModelData data = new ModelData();
        data.setUserName(userName);
        data.setAge(age);

        log.info("name = {}, age = {}", data.getUserName(), data.getAge());
        return "Request Param";
    }

    @ResponseBody
    @RequestMapping("/model-attr-v2")
    public String modelAttributeV2(@ModelAttribute ModelData data) {
        log.info("name = {}, age = {}", data.getUserName(), data.getAge());
        return "Request Param";
    }

    @ResponseBody
    @RequestMapping("/model-attr-v3")
    // @ModelAttribute 생략 가능.
    public String modelAttributeV3(ModelData data) {
        log.info("name = {}, age = {}", data.getUserName(), data.getAge());
        return "Request Param";
    }
}
