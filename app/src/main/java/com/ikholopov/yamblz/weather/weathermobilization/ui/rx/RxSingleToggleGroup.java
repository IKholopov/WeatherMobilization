package com.ikholopov.yamblz.weather.weathermobilization.ui.rx;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import com.nex3z.togglebuttongroup.SingleSelectToggleGroup;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

import static com.jakewharton.rxbinding2.internal.Preconditions.checkMainThread;
import static com.jakewharton.rxbinding2.internal.Preconditions.checkNotNull;

/**
 * Created by turist on 11.08.2017.
 */

public final class RxSingleToggleGroup {

    @CheckResult
    @NonNull
    public static Observable<Integer> selections(@NonNull SingleSelectToggleGroup view) {
        checkNotNull(view, "view == null");
        return new ToggleGroupSelectionsObservable(view);
    }

    private final static class ToggleGroupSelectionsObservable extends Observable<Integer> {
        private final SingleSelectToggleGroup view;

        ToggleGroupSelectionsObservable(SingleSelectToggleGroup view) {
            this.view = view;
        }

        @Override
        protected void subscribeActual(Observer<? super Integer> observer) {
            if (!checkMainThread(observer)) {
                return;
            }
            ToggleGroupSelectionsObservable.Listener listener = new ToggleGroupSelectionsObservable.Listener(view, observer);
            observer.onSubscribe(listener);
            view.setOnCheckedChangeListener(listener);
        }

        static final class Listener extends MainThreadDisposable
                implements SingleSelectToggleGroup.OnCheckedChangeListener {
            private final SingleSelectToggleGroup view;
            private final Observer<? super Integer> observer;

            Listener(SingleSelectToggleGroup view, Observer<? super Integer> observer) {
                this.view = view;
                this.observer = observer;
            }

            @Override
            protected void onDispose() {
                view.setOnCheckedChangeListener(null);
            }

            @Override
            public void onCheckedChanged(SingleSelectToggleGroup group, int checkedId) {
                if (!isDisposed()) {
                    observer.onNext(checkedId);
                }
            }
        }
    }
}