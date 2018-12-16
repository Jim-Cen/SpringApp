package pers.jim.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Isolation;

import pers.jim.app.model.Program;


@Mapper
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_UNCOMMITTED)
public interface ProgramMapper {
	public List<Program> getProgram();
}
