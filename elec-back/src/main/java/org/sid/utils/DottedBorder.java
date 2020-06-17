package org.sid.utils;

import com.itextpdf.text.pdf.PdfContentByte;

class DottedBorder extends CustomBorder {
    public DottedBorder(int border) { super(border); }
    public void setLineDash(PdfContentByte canvas) {
        canvas.setLineCap(PdfContentByte.LINE_CAP_ROUND);
        canvas.setLineDash(0, 4, 2);
    }
}
