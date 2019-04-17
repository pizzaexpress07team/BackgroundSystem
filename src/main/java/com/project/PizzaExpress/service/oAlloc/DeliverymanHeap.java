package com.project.PizzaExpress.service.oAlloc;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class DeliverymanHeap {

    private List<Vector<Object>> dms;//Vector : [0]d_id, [1]duration, [2]d to f path, [3]f to u path

    public DeliverymanHeap()
    {
        dms = new LinkedList<>();
    }

    public void insert(Vector<Object> dm)
    {
        dms.add(dm);
        int target = dms.size() - 1;
        int father = target / 2;
        if (dms.size() > 1)
        {
            while (target > 0)
            {
                if ((Double)dms.get(father).get(1) > (Double)dms.get(target).get(1))
                {
                    Vector<Object> temp = dms.get(father);
                    dms.set(father, dms.get(target));
                    dms.set(target, temp);
                    target = father;
                    father = target / 2;
                }
                else
                    break;
            }
        }
    }

    public Vector<Object> remove()
    {
        return dms.remove(0);
    }

    public int size()
    {
        return dms.size();
    }
}
