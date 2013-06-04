package de.novensa.techniques.java.web.jsp.customTagLibraries.util.JspHtmlSpaceReducer;

import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test case for the {@code JspHtmlSpaceReducer}.
 *
 * @author Daniel Schulz
 */
public class JspHtmlSpaceReducerTest {

    private static final String INPUT_FARBTEIL_TOOLTIP = "\n" +
            "                            \n" +
            "                                \n" +
            "                                     COLOR 101\n" +
            "                                                A1B2CD34\n" +
            "                                                PART A6:SOME MORE DETAILS AB.C012345/678\n" +
            "                                \n" +
            "\n" +
            "                                \n" +
            "\n" +
            "                                \n" +
            "                            \n" +
            "                        ";

    private static final String RESULT_FARBTEIL_TOOLTIP = "COLOR 101\n" +
            "A1B2CD34\n" +
            "PART A6:SOME MORE DETAILS AB.C012345/678";


    @Test
    public void testReduce() throws Exception {

        final JspHtmlSpaceReducer reducer = new JspHtmlSpaceReducer();
        final String reduced = reducer.reduce(INPUT_FARBTEIL_TOOLTIP);
        assertEquals("Same reduced tooltip", RESULT_FARBTEIL_TOOLTIP, reduced);
    }
}
