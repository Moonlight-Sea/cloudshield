package pers.sea.shield.dispatch.service.impl;

import pers.sea.shield.dispatch.pojo.entity.Dictionary;
import pers.sea.shield.dispatch.mapper.DictionaryMapper;
import pers.sea.shield.dispatch.service.IDictionaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author moon
 * @since 2023-12-12
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements IDictionaryService {

}
