package service;

import domain.MockData;

import java.util.List;

public interface WriterFileService {

    public void saveData(List<MockData> mockDataList);
}
