import { useEffect, useState } from "react";
import CategoryContext from "../contexts/CategoryContext";
import { categoryApi, elementApi } from "../services/api";

const CategoryProvider = ({ children }) => {
  const [categories, setCategories] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState(null);
  const [categoryElements, setCategoryElements] = useState({});
  const [usedElements, setUsedElements] = useState(new Set()); // Elementi già usati in TierList
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [initialized, setInitialized] = useState(false);

  // Dati di fallback per le categorie (sempre disponibili)
  const defaultCategories = [
    {
      id: 1,
      name: "Piatti Italiani",
      categoryName: "Piatti Italiani",
      descrizione: "I migliori piatti della tradizione italiana",
    },
    {
      id: 2,
      name: "Film",
      categoryName: "Film",
      descrizione: "I film più iconici di tutti i tempi",
    },
    {
      id: 3,
      name: "Videogiochi",
      categoryName: "Videogiochi",
      descrizione: "I videogiochi che hanno fatto la storia",
    },
  ];

  // Inizializza con categorie di default
  useEffect(() => {
    if (!initialized) {
      setCategories(defaultCategories);
      setSelectedCategory(defaultCategories[0]);
      setInitialized(true);

      // Prova a caricare le categorie dal backend in background
      loadCategoriesFromBackend();
    }
  }, [initialized]);

  // Carica le categorie dal backend (opzionale)
  const loadCategoriesFromBackend = async () => {
    try {
      const categoriesData = await categoryApi.getAllCategories();

      if (categoriesData && categoriesData.length > 0) {
        // Normalizza i dati per avere sempre name e categoryName
        const normalizedCategories = categoriesData.map((cat) => ({
          ...cat,
          name: cat.categoryName || cat.name,
          categoryName: cat.categoryName || cat.name,
        }));

        setCategories(normalizedCategories);

        // Mantieni la categoria selezionata se esiste nelle nuove categorie
        const currentSelectedId = selectedCategory?.id;
        const newSelected =
          normalizedCategories.find((cat) => cat.id === currentSelectedId) ||
          normalizedCategories[0];
        setSelectedCategory(newSelected);

        console.log("Categorie caricate dal backend:", normalizedCategories);
      }

      setError(null);
    } catch (err) {
      console.warn(
        "Impossibile caricare categorie dal backend, uso quelle di default:",
        err.message
      );
    }
  };

  // Carica gli elementi quando cambia la categoria selezionata
  useEffect(() => {
    if (selectedCategory && !categoryElements[selectedCategory.id]) {
      loadElementsForCategory(selectedCategory.id);
    }
  }, [selectedCategory]);

  const loadElementsForCategory = async (categoryId) => {
    if (categoryElements[categoryId]) {
      return; // Già caricati
    }

    try {
      setLoading(true);

      // Prova a caricare dal backend
      let filteredElements = [];

      try {
        if (elementApi.getElementsByCategory) {
          filteredElements = await elementApi.getElementsByCategory(categoryId);
        } else {
          // Fallback: carica tutti gli elementi e filtra
          const allElements = await elementApi.getAllElements();
          filteredElements = allElements.filter(
            (element) =>
              element.categoryId === categoryId ||
              element.category?.id === categoryId
          );
        }
      } catch (apiError) {
        console.warn("Errore API, uso dati di fallback:", apiError.message);
        filteredElements = [];
      }

      if (filteredElements.length > 0) {
        setCategoryElements((prev) => ({
          ...prev,
          [categoryId]: filteredElements.map((element) => ({
            id: element.id.toString(),
            name: element.name,
            imageUrl: element.imageUrl || element.image_url,
            categoryId: element.categoryId || element.category?.id,
            isUsed: false, // Inizialmente non utilizzato
          })),
        }));
      } else {
        // Usa dati di fallback
        setCategoryElements((prev) => ({
          ...prev,
          [categoryId]: getDefaultElementsForCategory(categoryId),
        }));
      }

      setError(null);
    } catch (err) {
      console.warn(
        `Impossibile caricare elementi per categoria ${categoryId}, uso quelli di default:`,
        err.message
      );

      setCategoryElements((prev) => ({
        ...prev,
        [categoryId]: getDefaultElementsForCategory(categoryId),
      }));
    } finally {
      setLoading(false);
    }
  };

  // Dati di fallback per le categorie
  const getDefaultElementsForCategory = (categoryId) => {
    const defaultElements = {
      1: [
        {
          id: "dish1",
          name: "Osso buco",
          imageUrl:
            "https://images.unsplash.com/photo-1603360946369-dc9bb6258143?w=200&auto=format",
          categoryId: 1,
          isUsed: false,
        },
        {
          id: "dish2",
          name: "Saltimbocca",
          imageUrl:
            "https://images.unsplash.com/photo-1563805042-7684c019e1cb?w=200&auto=format",
          categoryId: 1,
          isUsed: false,
        },
        {
          id: "dish3",
          name: "Parmigiana",
          imageUrl:
            "https://www.giallozafferano.it/images/ricette/201/20149/foto_hd/hd650x433_wm.jpg",
          categoryId: 1,
          isUsed: false,
        },
        {
          id: "dish4",
          name: "Fiorentina",
          imageUrl:
            "https://images.unsplash.com/photo-1558030006-450675393462?w=200&auto=format",
          categoryId: 1,
          isUsed: false,
        },
        {
          id: "dish5",
          name: "Orata al forno",
          imageUrl:
            "https://images.unsplash.com/photo-1519708227418-c8fd9a32b7a2?w=200&auto=format",
          categoryId: 1,
          isUsed: false,
        },
        {
          id: "dish6",
          name: "Cotoletta",
          imageUrl:
            "https://images.unsplash.com/photo-1601050690597-df0568f70950?w=200&auto=format",
          categoryId: 1,
          isUsed: false,
        },
      ],
      2: [
        {
          id: "movie1",
          name: "The Godfather",
          imageUrl:
            "https://images.unsplash.com/photo-1489599735734-79b4154f88d6?w=200&auto=format",
          categoryId: 2,
          isUsed: false,
        },
        {
          id: "movie2",
          name: "Pulp Fiction",
          imageUrl:
            "https://images.unsplash.com/photo-1440404653325-ab127d49abc1?w=200&auto=format",
          categoryId: 2,
          isUsed: false,
        },
        {
          id: "movie3",
          name: "Inception",
          imageUrl:
            "https://images.unsplash.com/photo-1536440136628-849c177e76a1?w=200&auto=format",
          categoryId: 2,
          isUsed: false,
        },
      ],
      3: [
        {
          id: "game1",
          name: "The Witcher 3",
          imageUrl:
            "https://images.unsplash.com/photo-1493711662062-fa541adb3fc8?w=200&auto=format",
          categoryId: 3,
          isUsed: false,
        },
        {
          id: "game2",
          name: "Red Dead Redemption 2",
          imageUrl:
            "https://images.unsplash.com/photo-1542751371-adc38448a05e?w=200&auto=format",
          categoryId: 3,
          isUsed: false,
        },
        {
          id: "game3",
          name: "Cyberpunk 2077",
          imageUrl:
            "https://images.unsplash.com/photo-1552820728-8b83bb6b773f?w=200&auto=format",
          categoryId: 3,
          isUsed: false,
        },
      ],
    };

    return defaultElements[categoryId] || [];
  };

  const changeCategory = (category) => {
    setSelectedCategory(category);
    setError(null);
  };

  // Restituisce solo gli elementi disponibili (non utilizzati)
  const getCurrentElements = () => {
    if (!selectedCategory) return [];
    const elements = categoryElements[selectedCategory.id] || [];
    return elements.filter((element) => !element.isUsed);
  };

  // Restituisce tutti gli elementi della categoria (anche quelli utilizzati)
  const getAllCategoryElements = () => {
    if (!selectedCategory) return [];
    return categoryElements[selectedCategory.id] || [];
  };

  // Marca un elemento come utilizzato
  const markElementAsUsed = (elementId) => {
    if (!selectedCategory) return;

    setCategoryElements((prev) => ({
      ...prev,
      [selectedCategory.id]:
        prev[selectedCategory.id]?.map((element) =>
          element.id === elementId ? { ...element, isUsed: true } : element
        ) || [],
    }));

    setUsedElements((prev) => new Set([...prev, elementId]));
  };

  // Marca un elemento come non utilizzato
  const markElementAsAvailable = (elementId) => {
    if (!selectedCategory) return;

    setCategoryElements((prev) => ({
      ...prev,
      [selectedCategory.id]:
        prev[selectedCategory.id]?.map((element) =>
          element.id === elementId ? { ...element, isUsed: false } : element
        ) || [],
    }));

    setUsedElements((prev) => {
      const newSet = new Set(prev);
      newSet.delete(elementId);
      return newSet;
    });
  };

  // Verifica se un elemento è utilizzato
  const isElementUsed = (elementId) => {
    return usedElements.has(elementId);
  };

  // Ottieni statistiche della categoria
  const getCategoryStats = () => {
    if (!selectedCategory) return { total: 0, available: 0, used: 0 };

    const elements = categoryElements[selectedCategory.id] || [];
    const total = elements.length;
    const used = elements.filter((e) => e.isUsed).length;
    const available = total - used;

    return { total, available, used };
  };

  const refreshCategory = () => {
    if (selectedCategory) {
      // Rimuovi gli elementi cached per forzare il reload
      setCategoryElements((prev) => {
        const newElements = { ...prev };
        delete newElements[selectedCategory.id];
        return newElements;
      });

      // Reset degli elementi utilizzati per questa categoria
      const elementsToRemove =
        categoryElements[selectedCategory.id]?.map((e) => e.id) || [];
      setUsedElements((prev) => {
        const newSet = new Set(prev);
        elementsToRemove.forEach((id) => newSet.delete(id));
        return newSet;
      });

      // Ricarica gli elementi
      loadElementsForCategory(selectedCategory.id);
    }

    // Ricarica anche le categorie
    loadCategoriesFromBackend();
  };

  return (
    <CategoryContext.Provider
      value={{
        categories,
        selectedCategory,
        changeCategory,
        getCurrentElements,
        getAllCategoryElements,
        loading,
        error,
        refreshCategory,
        markElementAsUsed,
        markElementAsAvailable,
        isElementUsed,
        getCategoryStats,
      }}
    >
      {children}
    </CategoryContext.Provider>
  );
};

export default CategoryProvider;
