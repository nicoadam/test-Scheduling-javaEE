package repository;

import domain.MockData;

import java.util.List;

public interface MockRepositoryService {

     void cleanTable();

     void saveMockData(List<MockData> mockData);

}
