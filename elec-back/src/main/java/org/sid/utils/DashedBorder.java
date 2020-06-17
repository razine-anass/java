package org.sid.utils;

import com.itextpdf.text.pdf.PdfContentByte;

class DashedBorder extends CustomBorder {
    public DashedBorder(int border) { super(border); }
    public void setLineDash(PdfContentByte canvas) {
        canvas.setLineDash(3, 3);
    }
}
