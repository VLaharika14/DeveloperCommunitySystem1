package com.communitydeveloper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.dcs.dao.DeveloperDao;
import com.dcs.dto.DeveloperDTO;
import com.dcs.dto.PostDTO;
import com.dcs.entity.Developer;
import com.dcs.entity.Post;
import com.dcs.exception.DeveloperCommunitySystemException;
import com.dcs.service.DeveloperServiceImpl;

@ExtendWith(MockitoExtension.class)
public class DeveloperServiceImplTest {

    @Mock
    private DeveloperDao developerDao;

    @InjectMocks
    private DeveloperServiceImpl developerService;

    
    @Test
    public void testGetDeveloperByReputation() {
        List<DeveloperDTO> expectedDeveloperDTOs = new ArrayList<>();
        List<Developer> developers = new ArrayList<>();
        when(developerDao.findByReputation(anyInt())).thenReturn(developers);

        List<DeveloperDTO> result = developerService.getDeveloperByReputation(12);

        assertEquals(expectedDeveloperDTOs, result);
        verify(developerDao, times(1)).findByReputation(anyInt());
    }

    

    // Add more test methods for other service methods...

    @Test
    public void testDeleteDeveloper() {
        Integer devId = 1;
        Developer developer = new Developer();
        when(developerDao.findById(anyInt())).thenReturn(Optional.of(developer));

        String result = developerService.delete(devId);

        assertEquals("Developer Deleted Successfully", result);
        verify(developerDao, times(1)).deleteById(anyInt());
    }
}

