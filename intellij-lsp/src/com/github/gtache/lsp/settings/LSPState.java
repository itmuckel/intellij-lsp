package com.github.gtache.lsp.settings;

import com.github.gtache.lsp.PluginMain;
import com.github.gtache.lsp.client.languageserver.serverdefinition.UserConfigurableServerDefinition;
import com.github.gtache.lsp.client.languageserver.serverdefinition.UserConfigurableServerDefinition$;
import com.github.gtache.lsp.requests.Timeout;
import com.github.gtache.lsp.requests.Timeouts;
import com.github.gtache.lsp.utils.ApplicationUtils$;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.ui.Messages;
import com.intellij.util.xmlb.XmlSerializationException;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import scala.Array;
import scala.Tuple2;

import java.util.*;

@State(name = "LSPState", storages = @Storage(file = "LSPState.xml"))

/**
 * Class representing the state of the LSP settings
 */
public final class LSPState implements PersistentStateComponent<LSPState> {

    private static final Logger LOG = Logger.getInstance(LSPState.class);

    //Must be public to be saved
    public boolean alwaysSendRequests = false;
    public Map<String, String[]> extToServ = new LinkedHashMap<>(10);
    public Map<Timeouts, Integer> timeouts = new EnumMap<>(Timeouts.class);
    public List<String> coursierResolvers = new ArrayList<>(5);
    public Map<String[], String[]> forcedAssociations = new HashMap<>(10);

    public LSPState() {
    }

    @Nullable
    public static LSPState getInstance() {
        try {
            return ServiceManager.getService(LSPState.class);
        } catch (final Exception e) {
            LOG.warn("Couldn't load LSPState");
            LOG.warn(e);
            ApplicationUtils$.MODULE$.invokeLater(() -> Messages.showErrorDialog("Couldn't load LSP settings, you will need to reconfigure them.", "LSP plugin"));
            return null;
        }
    }

    public List<String> getCoursierResolvers() {
        return coursierResolvers;
    }

    public void setCoursierResolvers(final Collection<String> coursierResolvers) {
        this.coursierResolvers = new ArrayList<>(coursierResolvers);
    }

    public Map<String, UserConfigurableServerDefinition> getExtToServ() {
        return UserConfigurableServerDefinition$.MODULE$.fromArrayMap(extToServ);
    }

    public void setExtToServ(final Map<String, UserConfigurableServerDefinition> extToServ) {
        this.extToServ = UserConfigurableServerDefinition$.MODULE$.toArrayMap(extToServ);
    }

    public boolean isAlwaysSendRequests() {
        return alwaysSendRequests;
    }

    public void setAlwaysSendRequests(final boolean b) {
        this.alwaysSendRequests = b;
    }

    public Map<Timeouts, Integer> getTimeouts() {
        return timeouts;
    }

    public void setTimeouts(final Map<Timeouts, Integer> timeouts) {
        this.timeouts = new EnumMap<>(timeouts);
    }

    public Map<String[], String[]> getForcedAssociations() {
        return forcedAssociations;
    }

    public void setForcedAssociations(final Map<String[], String[]> forcedAssociations) {
        this.forcedAssociations = new HashMap<>(forcedAssociations);
    }

    @Override
    public LSPState getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull final LSPState lspState) {
        try {
            XmlSerializerUtil.copyBean(lspState, this);
            LOG.info("LSP State loaded");
            if (extToServ != null && !extToServ.isEmpty()) {
                PluginMain.setExtToServerDefinition(UserConfigurableServerDefinition$.MODULE$.fromArrayMap(extToServ));
            }
            if (timeouts != null && !timeouts.isEmpty()) {
                Timeout.setTimeouts(timeouts);
            }
            if (forcedAssociations != null && !forcedAssociations.isEmpty()) {
                PluginMain.setForcedAssociations(forcedAssociations);
            }
        } catch (final Exception e) {
            LOG.warn("Couldn't load LSPState");
            LOG.warn(e);
            ApplicationUtils$.MODULE$.invokeLater(() -> Messages.showErrorDialog("Couldn't load LSP settings, you will need to reconfigure them.", "LSP plugin"));
        }
    }

}
