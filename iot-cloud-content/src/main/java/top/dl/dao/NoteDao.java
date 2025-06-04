package top.dl.dao;

import org.apache.ibatis.annotations.Mapper;
import top.dl.entity.Note;
import top.dl.framework.mybatis.dao.BaseDao;

import java.util.List;
import java.util.Map;

@Mapper
public interface NoteDao extends BaseDao<Note> {
 List<Note> getList(Map<String, Object> params);
}