package controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class TimeJobController {

	@Scheduled
	public void timeJob() {
		System.out.println("定时任务执行····");
	}
}
