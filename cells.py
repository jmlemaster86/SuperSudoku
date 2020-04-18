#Writing code for 81 sudoku cells is bullshit so this will autogenerate alot of it for you

cells = open("cells.xml",'w')
tab = "\t"
endline = "\n"
for i in range(81):
    line1 = tab + "<TextView" + endline
    line2 = tab + tab + "android:id=\"@+id/Cell" + str(i) + "\"" + endline
    line3 = tab + tab + "android:layout_width=\"40dp\"" + endline
    line4 = tab + tab + "android:layout_height=\"40dp\"" + endline
    line5 = tab + tab + "android:layout_margin=\"1dp\"" + endline
    line6 = tab + tab + "android:background=\"@android:color/background_light\"" + endline
    line7 = tab + tab + "android:gravity=\"center\"" + endline
    line8 = tab + tab + "android:text=\"@{board.getSquare(" + str(i) + ")}\"" + endline
    line9 = tab + tab + "android:textAllCaps=\"true\"" + endline
    line10 = tab + tab + "android:textAppearance=\"@style/TextAppearance.AppCompat.Display2\"" + endline
    line11 = tab + tab + "android:textSize=\"20sp\"" + endline
    line12 = endline

    cells.write(line1)
    cells.write(line2)
    cells.write(line3)
    cells.write(line4)
    cells.write(line5)
    cells.write(line6)
    cells.write(line7)
    cells.write(line8)
    cells.write(line9)
    cells.write(line10)
    cells.write(line11)
    cells.write(line12)
    cells.write(line12)
    cells.write(line12)

    #Constraints must still be done manually
cells.close()