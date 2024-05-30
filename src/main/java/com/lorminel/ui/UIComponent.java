package com.lorminel.ui;

import com.lorminel.service.Session;

public interface UIComponent {

    Session render(Session session);
}
