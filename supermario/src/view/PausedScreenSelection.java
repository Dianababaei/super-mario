package view;

public enum PausedScreenSelection {
    MUTE(1),
    EXIT(2),
    CONTINUE(3);

    private final int LineNumber;

    PausedScreenSelection(int LineNumber){
        this.LineNumber = LineNumber;
    }
    public PausedScreenSelection getSelection(int line){
        if (line == 0){
            return MUTE;
        } else if (line == 1) {
            return EXIT;
        } else if (line == 2) {
            return CONTINUE;
        }
        else {
            return null;
        }
    }

    public PausedScreenSelection select(boolean toUp) {
        int selection;
        if (LineNumber > -1 && LineNumber < 3) {
            selection = LineNumber - (toUp ? 1 : -1);
            if (selection == -1)
                selection = 2;
            else if (selection == 3)
                selection = 0;
            return getSelection(selection);
        }
            return null;

        }
        public int getLineNumber() {
        return LineNumber;
        }









}


