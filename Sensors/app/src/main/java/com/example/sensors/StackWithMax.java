package com.example.sensors;

import java.util.Stack;

public class StackWithMax {

    Stack<Float> mainStack = new Stack<Float>();
    Stack<Float> trackStack = new Stack<Float>();

    public void push(float x)
    {
        mainStack.push(x);
        if (mainStack.size() == 1)
        {
            trackStack.push(x);
            return;
        }

        // If current element is greater than
        // the top element of track stack, push
        // the current element to track stack
        // otherwise push the element at top of
        // track stack again into it.
        if (x > trackStack.peek())
            trackStack.push(x);
        else
            trackStack.push(trackStack.peek());
    }

    public float getMax()
    {
        return trackStack.peek();
    }

    public void pop()
    {
        mainStack.pop();
        trackStack.pop();
    }
}
