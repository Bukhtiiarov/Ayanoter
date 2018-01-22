package org.masa.ayanoter.presentation;

import org.masa.ayanoter.dataAccess.User;

/**
 * Created by mikle on 12/27/17.
 */
public interface IProfileStatViewModelProvider {
    ProfileStatViewModel get(User user);
}
