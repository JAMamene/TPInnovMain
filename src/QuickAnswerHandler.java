public class QuickAnswerHandler {

    void handle (Answer answer) {
        if (!(answer.getStatus().equals(Answer.NORMAL_STATUS))) {
            System.out.println("Whoops, looks like something went wrong!");
            return;
        }
        if (answer.getAnswer() == null) {
            System.out.println("You request was successful");
        }
        else {
            System.out.println("Here is the answer to your request :\n" + answer.getAnswer());
        }
    }
}
