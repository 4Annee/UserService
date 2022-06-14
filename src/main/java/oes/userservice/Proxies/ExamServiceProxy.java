package oes.userservice.Proxies;

import oes.userservice.Entities.Assessment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "ExamService")
public interface ExamServiceProxy {
    @GetMapping("/api/Exam/Modules")
    public List<Assessment> getAssessments(String[] modules);
}
