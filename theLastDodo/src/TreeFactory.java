import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * TreeFactory with Up/Down Tree selection
 */
public class TreeFactory {

    private static int TREECOUNT = 0;

    private static int SAMECHOICE = 0;

    public static Picture getNewPicture() {

        Picture picture;

        if (TREECOUNT % 2 == 0) {

            int newChoice = SAMECHOICE = randomChoice();
            
            picture = new Picture(Grid.columnToX(87), Grid.PADDING, randomPictureTop(newChoice));
        } else {
            picture = new Picture(Grid.columnToX(87), Grid.rowToY(randomHeightBottom(SAMECHOICE)),randomPictureDown(SAMECHOICE));
        }

        TREECOUNT++;

        return picture;

    }


    private static int randomChoice() {
        return (int) Math.ceil(Math.random()*5);
    }


    private static int randomHeightBottom(int choice){

        int height = 0;

        switch (choice) {

            case 1:
                height=25;
                break;
            case 2:
                height=30;
                break;
            case 3:
                height=35;
                break;
            case 4:
                height=40;
                break;
            case 5:
                height=45;
                break;
        }
        return height;
    }


    private static String randomPictureTop(int choice){

        String file = "";

        switch (choice) {

            case 1:
                file="TOP1.png";
                break;
            case 2:
                file="TOP2.png";
                break;
            case 3:
                file="TOP3.png";
                break;
            case 4:
                file="TOP4.png";
                break;
            case 5:
                file="TOP5.png";
                break;
        }
        return file;
    }


    private static String randomPictureDown(int choice){

        String file = "";

        switch (choice) {

            case 1:
                file="BOT5.png";
                break;
            case 2:
                file="BOT4.png";
                break;
            case 3:
                file="BOT3.png";
                break;
            case 4:
                file="BOT2.png";
                break;
            case 5:
                file="BOT1.png";
                break;
        }
        return file;
    }
}