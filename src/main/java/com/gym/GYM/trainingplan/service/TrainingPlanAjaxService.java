package com.gym.GYM.trainingplan.service;

import java.util.List;



import com.gym.GYM.trainingplan.dto.TrainingDTO;

public interface TrainingPlanAjaxService {

	List<TrainingDTO> sendGrade(String trainingPlanGrade);

}
