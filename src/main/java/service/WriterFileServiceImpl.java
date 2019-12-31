package service;

import domain.MockData;
import repository.MockRepositoryService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Optional;

@Named
@Stateless
public class WriterFileServiceImpl implements WriterFileService{

    @Inject
    private MockRepositoryService mockRepositoryService;

    @Override
    public void saveData(List<MockData> mockDataList) {
        if ( Optional.of(mockDataList).isPresent() ) {
             this.mockRepositoryService.cleanTable();
             this.mockRepositoryService.saveMockData(mockDataList);
        }
    }
}
