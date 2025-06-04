package top.dl.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.dl.dto.NoteDTO;
import top.dl.entity.Note;
import top.dl.vo.NoteVO;
import java.util.List;
/**
 * @author minder
 **/
@Mapper
public interface NoteConvert {
 NoteConvert INSTANCE = Mappers.getMapper(NoteConvert.class);
 NoteVO convert(Note entity);
 List<NoteVO> convertList(List<Note> list);
 Note convert(NoteDTO noteDTO);
}