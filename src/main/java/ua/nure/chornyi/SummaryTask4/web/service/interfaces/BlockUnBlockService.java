package ua.nure.chornyi.SummaryTask4.web.service.interfaces;

public interface BlockUnBlockService {

    /**
     * Returns status blocked.
     * @param userID user id who status you need
     */
    void blockUser(int userID);

    /**
     * Returns status unblocked.
     * @param userID user id who status you need
     */
    void unblockUser(int userID);
}
