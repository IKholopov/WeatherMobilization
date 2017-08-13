package com.ikholopov.yamblz.weather.weathermobilization.ui;

import android.content.Context;
import android.content.res.Resources;

import com.ikholopov.yamblz.weather.weathermobilization.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.threeten.bp.Duration;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by turist on 14.08.2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class UpdateIntervalFormatTest {

    @Mock Context context;

    UpdateIntervalFormat format;

    @Before
    public void setUp() {
        Resources resources = mock(Resources.class);
        when(context.getResources()).thenReturn(resources);
        when(resources.getStringArray(R.array.update_intervals)).thenReturn(new String[]{"0", "1", "2", "3", "4"});
        format = new UpdateIntervalFormat(context);
    }

    @Test
    public void getString() throws Exception {
        assertEquals(format.getString(Duration.ZERO), "0");
        assertEquals(format.getString(Duration.ofMinutes(15)), "1");
        assertEquals(format.getString(Duration.ofMinutes(30)), "2");
        assertEquals(format.getString(Duration.ofHours(1)), "3");
        assertEquals(format.getString(Duration.ofHours(2)), "4");
    }

    @Test
    public void getInterval() throws Exception {
        assertEquals(format.getInterval(0), Duration.ZERO);
        assertEquals(format.getInterval(1), Duration.ofMinutes(15));
        assertEquals(format.getInterval(2), Duration.ofMinutes(30));
        assertEquals(format.getInterval(3), Duration.ofHours(1));
        assertEquals(format.getInterval(4), Duration.ofHours(2));
    }
}