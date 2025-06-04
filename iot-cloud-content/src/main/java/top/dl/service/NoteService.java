package top.dl.service;

import top.dl.dto.NoteDTO;
import top.dl.entity.Note;
import top.dl.framework.common.utils.PageResult;
import top.dl.framework.mybatis.service.BaseService;
import top.dl.query.NoteQuery;
import top.dl.vo.NoteVO;

public interface NoteService extends BaseService<Note> {
 PageResult<NoteVO> page(NoteQuery query);
 PageResult<NoteVO> getNotesByCategoryId(Long categoryId, NoteQuery query);
 boolean publishNote(NoteDTO noteDTO);
}