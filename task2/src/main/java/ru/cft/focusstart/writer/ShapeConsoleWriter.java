package ru.cft.focusstart.writer;

import ru.cft.focusstart.Printable;

class ShapeConsoleWriter implements ShapeWriter {

    @Override
    public void write(Printable object, String units, String squareUnits) {
        System.out.println(object.getPrintText(units, squareUnits));
    }

}
