import {Injectable} from "@angular/core";
import {TranslationService} from "path-framework/app/path-framework/service/translation.service";

@Injectable()
export class DevOpsTranslationService extends TranslationService {

    private myTranslations = this.createTranslationMap(this.getDevOpsTranslations());

    public getSupportedLanguageCodes(): string[] {
        return ["en", "de", "it", "nl"];
    }

    protected getTranslation(key: string): string {
        // prefer custom translations
        if (this.myTranslations.get(key) == null) {
            return super.getTranslation(key);
        }
        return this.myTranslations.get(key);
    }

    private getDevOpsTranslations() {
        const languageCode: string = this.getUserLanguage();

        // put additional application translations here
        // if (languageCode === "de") { TODO add language support
        return {
            // default translations, TODO remove when language support is added
            "Back": "Zurück",
            "Cancel": "Abbrechen",
            "Delete": "Löschen",
            "DeleteWarningQuestion": "Wollen Sie diesen Datensatz löschen?",
            "Detail": "Detail",
            "Logout": "Abmelden",
            "MainMenu": "Hauptmenü",
            "New": "Neu",
            "NotSignedIn": "Nicht angemeldet",
            "Ok": "OK",
            "Result": "Resultat",
            "Results": "Resultate",
            "Search": "Suche",
            "SearchInputLabel": "Suchbegriff",
            "SearchTextTooShort": "Suchbegriff zu kurz",
            "SignedInAs": "Angemeldet als",
            "Translation": "Übersetzung",
            "Translations": "Übersetzungen",
            "de": "Deutsch",
            "en": "Englisch"
        };
    }

}
