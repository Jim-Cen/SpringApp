package pers.jim.app;

import java.util.ArrayList;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pers.jim.app.model.Program;

@ApiModel(description="程序列表")
public class ProgramList {
	@ApiModelProperty(value="程序列表")
	ArrayList<Program> programs;

	public ArrayList<Program> getPrograms() {
		return programs;
	}

	public void setPrograms(ArrayList<Program> programs) {
		this.programs = programs;
	}
	
}
