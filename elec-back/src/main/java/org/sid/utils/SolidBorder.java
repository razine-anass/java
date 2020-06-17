package org.sid.utils;

import com.itextpdf.text.pdf.PdfContentByte;

class SolidBorder extends CustomBorder {
    public SolidBorder(int border) { super(border); }
    public void setLineDash(PdfContentByte canvas) {}
}
