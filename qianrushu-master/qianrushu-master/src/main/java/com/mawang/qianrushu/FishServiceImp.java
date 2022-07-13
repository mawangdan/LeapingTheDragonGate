package com.mawang.qianrushu;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mawang.qianrushu.mapper.FishMapper;
import org.springframework.stereotype.Service;

@Service("fishService")
public class FishServiceImp  extends ServiceImpl<FishMapper, Fish> implements FishService{
}
