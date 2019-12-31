package services;

import domain.MockData;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import service.ReaderFileService;
import service.WriterFileService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class ServicesTest {

    @Mock
    ReaderFileService readerFileService;

    @Mock
    WriterFileService writerFileService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void readerTest() {
        List<MockData> list = new ArrayList<>();
        list.add(new MockData((long)1, "kiko", "kiko", "kiko@gmail.com", "male", "10.0.0.1"));
        list.add(new MockData((long)1, "juan", "palote", "juan@gmail.com", "male", "10.0.2.1"));

        when(this.readerFileService.getListDataMock()).thenReturn(list);
    }

    @Test
    public void writerTest() {

     }
}
