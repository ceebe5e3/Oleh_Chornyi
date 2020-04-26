package ua.nure.chornyi.SummaryTask4.web.service;

import ua.nure.chornyi.SummaryTask4.db.dao.interfaces.BlockUnBlockUserDao;
import ua.nure.chornyi.SummaryTask4.web.service.interfaces.BlockUnBlockService;

/**
 * {@link BlockUnBlockService} implementation for work with MySQL database
 */
public class BlockUnBlockServiceImpl implements BlockUnBlockService {

    private BlockUnBlockUserDao blockUnBlockUserDao;

    public BlockUnBlockServiceImpl(BlockUnBlockUserDao blockUnBlockUserDao) {
        this.blockUnBlockUserDao = blockUnBlockUserDao;
    }

    /**
     * Returns status blocked.
     * @param userID user id who status you need
     */
    @Override
    public void blockUser(int userID) {
        blockUnBlockUserDao.blockUser(userID);
    }

    /**
     * Returns status unblocked.
     * @param userID user id who status you need
     */
    @Override
    public void unblockUser(int userID) {
        blockUnBlockUserDao.unblockUser(userID);
    }
}
