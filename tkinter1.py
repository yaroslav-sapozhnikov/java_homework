from tkinter import *
from math import sin, cos

root = Tk()

c = Canvas(root, width=600, height=600, bg='white')
c.pack()

r = 200
c.create_oval(300-r, 300-r, 300+r, 300+r)

r = 10
circle = c.create_oval(300-r, 100-r, 300+r, 100+r, fill='red')

e = 0

def circle_move():

    global c
    global e
  
    c.move(circle, cos(e), sin(e))
    e += 0.005
 
    root.after(10, circle_move)


circle_move()
root.mainloop()
 