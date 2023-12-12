package util;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SudokuUtilities {

    public enum SudokuLevel {EASY, EASY_REVERSE, MEDIUM, MEDIUM_REVERSE, HARD, HARD_REVERSE}

    public static final int GRID_SIZE = 9;
    public static final int SECTIONS_PER_ROW = 3;
    public static final int SECTION_SIZE = 3;

    /**
     * Create a 3-dimensional matrix with initial values and solution in Sudoku.
     *
     * @param level The level, i.e. the difficulty, of the initial standing.
     * @return A 3-dimensional int matrix.
     * [row][col][0] represents the initial values, zero representing an empty cell.
     * [row][col][1] represents the solution.
     * @throws IllegalArgumentException if the length of stringRepresentation is not 2*81 characters and
     *                                  for characters other than '0'-'9'.
     */
    public static int[][][] generateSudokuMatrix(SudokuLevel level) {
        String representationString = getRepresentationString(level);
        representationString = shuffleSudokuString(representationString);
        return convertStringToIntMatrix(representationString);
    }

    private static String getRepresentationString(SudokuLevel level) {
        return switch (level) {
            case EASY, EASY_REVERSE -> easy;
            case MEDIUM, MEDIUM_REVERSE -> medium;
            case HARD, HARD_REVERSE -> hard;
            // Add more cases if necessary
        };
    }

    private static String shuffleSudokuString(String input) {
        List<Character> numbers = Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9');
        Collections.shuffle(numbers);

        Map<Character, Character> mapping = new HashMap<>();
        for (int i = 0; i < numbers.size(); i++) {
            mapping.put(Character.forDigit(i + 1, 10), numbers.get(i));
        }

        StringBuilder shuffledString = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c >= '1' && c <= '9') {
                shuffledString.append(mapping.get(c));
            } else {
                shuffledString.append(c);
            }
        }

        return shuffledString.toString();
    }

    /**
     * Create a 3-dimensional matrix with initial values and solution in Sudoku.
     *
     * @param stringRepresentation A string of 2*81 characters, 0-9. The first 81 characters represents
     *                             the initial values, '0' representing an empty cell.
     *                             The following 81 characters represents the solution.
     * @return A 3-dimensional int matrix.
     * [row][col][0] represents the initial values, zero representing an empty cell.
     * [row][col][1] represents the solution.
     * @throws IllegalArgumentException if the length of stringRepresentation is not 2*81 characters and
     *                                  for characters other than '0'-'9'.
     */
    /*package private*/
    static int[][][] convertStringToIntMatrix(String stringRepresentation) {
        if (stringRepresentation.length() != GRID_SIZE * GRID_SIZE * 2)
            throw new IllegalArgumentException("representation length " + stringRepresentation.length());

        int[][][] values = new int[GRID_SIZE][GRID_SIZE][2];
        char[] charRepresentation = stringRepresentation.toCharArray();

        int charIndex = 0;
        // initial values
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                values[row][col][0] =
                        convertCharToSudokuInt(charRepresentation[charIndex++]);
            }
        }

        // solution values
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                values[row][col][1] =
                        convertCharToSudokuInt(charRepresentation[charIndex++]);
            }
        }

        return values;
    }

    private static int convertCharToSudokuInt(char ch) {
        if (ch < '0' || ch > '9') throw new IllegalArgumentException("character " + ch);
        return ch - '0';
    }

    private static final String easy =
            "000914070" + // 0 så printas "" annars printas 9,1,4 etc. Sparas i e
                    "010000054" +
                    "040002000" +
                    "007569001" +
                    "401000500" +
                    "300100000" +
                    "039000408" +
                    "650800030" +
                    "000403260" + // solution values after this substring
                    "583914672" +
                    "712386954" +
                    "946752183" +
                    "827569341" +
                    "461238597" +
                    "395147826" +
                    "239675418" +
                    "654821739" +
                    "178493265";
    private static final String easyReverse =
            "000941070" +
                    "040000051" +
                    "010002000" +
                    "007569004" +
                    "104000500" +
                    "300400000" +
                    "039000108" +
                    "650800030" +
                    "000103260" +
                    "583941672" +
                    "742386951" +
                    "916752483" +
                    "827569314" +
                    "164238597" +
                    "395417826" +
                    "239675148" +
                    "651824739" +
                    "478193265";


    private static final String medium =
            "300000010" +
                    "000050906" +
                    "050401200" +
                    "030000080" +
                    "002069400" +
                    "000000002" +
                    "900610000" +
                    "200300058" +
                    "100800090" +
                    "324976815" +
                    "718253946" +
                    "659481273" +
                    "536142789" +
                    "872569431" +
                    "491738562" +
                    "985617324" +
                    "267394158" +
                    "143825697";
    private static final String mediumReverse =
            "500000010" +
                    "000030906" +
                    "030401200" +
                    "050000080" +
                    "002069400" +
                    "000000002" +
                    "900610000" +
                    "200500038" +
                    "100800090" +
                    "524976813" +
                    "718235946" +
                    "639481275" +
                    "356142789" +
                    "872369451" +
                    "491758362" +
                    "983617524" +
                    "267594138" +
                    "145823697";

    private static final String hard =
            "030600000" +
                    "000010070" +
                    "080000000" +
                    "000020000" +
                    "340000800" +
                    "500030094" +
                    "000400000" +
                    "150800200" +
                    "700006050" +
                    "931687542" +
                    "465219378" +
                    "287345916" +
                    "876924135" +
                    "349561827" +
                    "512738694" +
                    "693452781" +
                    "154873269" +
                    "728196453";
    private static final String hardReverse =
            "060300000" +
                    "000010070" +
                    "080000000" +
                    "000020000" +
                    "640000800" +
                    "500060094" +
                    "000400000" +
                    "150800200" +
                    "700003050" +
                    "961387542" +
                    "435219678" +
                    "287645913" +
                    "873924165" +
                    "649531827" +
                    "512768394" +
                    "396452781" +
                    "154876239" +
                    "728193456";
}
