package networkingC;

import Shared.User;

public interface Client {
    void searchBook(String bookName);
    void sendInfo(String jsonInstruction);
}
