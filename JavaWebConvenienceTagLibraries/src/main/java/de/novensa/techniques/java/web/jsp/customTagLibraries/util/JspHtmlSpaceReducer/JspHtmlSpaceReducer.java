package de.novensa.techniques.java.web.jsp.customTagLibraries.util.JspHtmlSpaceReducer;

import org.apache.commons.lang3.StringUtils;
import org.javatuples.Pair;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Reduces the space in the HTML string originating from the JSP indenting.
 *
 * @author Daniel Schulz
 */
public class JspHtmlSpaceReducer extends BodyTagSupport {

    // constants
    private final static String EMPTY = "";

    private final static String SPACE = " ";
    private final static String DOUBLE_SPACE = SPACE + SPACE;

    private final static String LINE_FEED = "\n";
    private final static String DOUBLE_LINE_FEED = LINE_FEED + LINE_FEED;

    private final static String CARRIAGE_RETURN = "\r";


    // field members
    private final Set<Pair<String, String>> set = new HashSet<Pair<String, String>>(8);
    private boolean addedSimpleSpaces = false;
    {
        set.add(new Pair<String, String>(DOUBLE_LINE_FEED, LINE_FEED));
        set.add(new Pair<String, String>(CARRIAGE_RETURN, EMPTY));
        set.add(new Pair<String, String>(DOUBLE_SPACE, EMPTY));
    }


    public String reduce(final CharSequence input) {
        StringBuilder res = new StringBuilder();

        // init compiled patterns and replace occurred patterns by replacements
        String[] strings = input.toString().split(LINE_FEED);
        String h;
        for (String cur : strings) {
            h = cur.trim();

            if (!StringUtils.isEmpty(h)) {
                if (!StringUtils.isEmpty(res)) {
                    res.append(LINE_FEED);
                }
                res.append(h);
            }
        }



        return res.toString();
    }

    @Override
    public int doAfterBody() throws JspException {

        final JspWriter writer = getBodyContent().getEnclosingWriter();
        try {
            writer.print(this.reduce(getBodyContent().getString()));
        } catch (IOException e) {
            e.printStackTrace();
        }


        return EVAL_BODY_INCLUDE;
    }


    // constructor
    public JspHtmlSpaceReducer() {
    }


    // special setter
    @SuppressWarnings("UnusedDeclaration")
    public void setPatterns(Collection<Pair<String, String>> collection) {
        set.clear();

        for (Pair<String, String> pair : collection) {
            set.add(pair);
        }
    }

    public void setAddedSimpleSpaces(final boolean addedSimpleSpaces) {
        this.addedSimpleSpaces = addedSimpleSpaces;
        if (this.addedSimpleSpaces) {
            set.add(new Pair<String, String>(SPACE, EMPTY));
        }
    }

    // setter / getter
    @SuppressWarnings("UnusedDeclaration")
    public Set getSet() {
        return set;
    }

    public boolean isAddedSimpleSpaces() {
        return addedSimpleSpaces;
    }
}
