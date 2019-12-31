package schedule;


import domain.MockData;
import org.apache.log4j.Logger;
import service.ReaderFileService;
import service.WriterFileService;

import javax.ejb.Schedule;
import javax.ejb.Schedules;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.util.List;

@Singleton
public class MockDataSchedule {

    private final static Logger logger = Logger.getLogger(MockDataSchedule.class);

    @Inject
    private ReaderFileService readerFileService;

    @Inject
    private WriterFileService writerFileService;

    //@Schedule(second = "*/5", minute = "*", hour = "*", persistent = false) // each 5 second...
    @Schedule(dayOfWeek = "*", hour = "13", minute = "35", persistent = false)
    public void executeTask() {
        logger.info("----------- Reading file");
        List<MockData> mockDataList = this.readerFileService.getListDataMock();
        this.writerFileService.saveData(mockDataList);
    }
}
