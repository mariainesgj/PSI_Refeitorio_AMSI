package pt.ipleiria.estg.dei.refeitorio.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class GenericViewModelFactory<T extends ViewModel, R> implements ViewModelProvider.Factory {
    private final R repository;
    private final Class<T> viewModelClass;

    public GenericViewModelFactory(Class<T> viewModelClass, R repository) {
        this.viewModelClass = viewModelClass;
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(viewModelClass)) {
            try {
                return (T) viewModelClass.getConstructor(repository.getClass()).newInstance(repository);
            } catch (Exception e) {
                throw new RuntimeException("Error creating ViewModel with repository", e);
            }
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
