import React, { useState, useEffect } from "react";
import Element from "./Element";
import useTier from "../hooks/useTier";
import useCategory from "../hooks/useCategory";
import { elementApi } from "../services/api";
import "./ElementRooster.css";

const ElementRooster = () => {
  const { getElementTier } = useTier();
  const { selectedCategory, getCurrentElements, loading: categoryLoading } = useCategory();
  const [availableElements, setAvailableElements] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Carica gli elementi dal backend quando cambia la categoria
  useEffect(() => {
    const loadElements = async () => {
      if (!selectedCategory) {
        setAvailableElements([]);
        setLoading(false);
        return;
      }

      try {
        setLoading(true);
        
        // Prima prova a ottenere gli elementi dalla categoria context
        const categoryElements = getCurrentElements();
        
        if (categoryElements && categoryElements.length > 0) {
          // Usa gli elementi già caricati dal CategoryProvider
          setAvailableElements(categoryElements);
          setError(null);
        } else {
          // Fallback: carica tutti gli elementi e filtra per categoria
          const elements = await elementApi.getAllElements();
          
          // Filtra per categoria corrente
          const filteredElements = elements.filter(element => 
            element.categoryId === selectedCategory.id || 
            element.category?.id === selectedCategory.id
          );
          
          // Trasforma i dati dal backend nel formato richiesto dal frontend
          const formattedElements = filteredElements.map(element => ({
            id: element.id.toString(),
            name: element.name,
            imageUrl: element.imageUrl,
            categoryId: element.categoryId || element.category?.id
          }));
          
          setAvailableElements(formattedElements);
          setError(null);
        }
        
      } catch (err) {
        console.error('Errore nel caricamento degli elementi:', err);
        setError('Errore nel caricamento degli elementi');
        
        // Fallback ai dati statici in caso di errore, basati sulla categoria
        const fallbackData = getFallbackDataForCategory(selectedCategory.id);
        setAvailableElements(fallbackData);
      } finally {
        setLoading(false);
      }
    };

    loadElements();
  }, [selectedCategory, getCurrentElements]);

  // Dati di fallback basati sulla categoria
  const getFallbackDataForCategory = (categoryId) => {
    const fallbackCategories = {
      1: [ // Piatti Italiani
        {
          id: "dish1",
          name: "Osso buco",
          imageUrl: "https://images.unsplash.com/photo-1603360946369-dc9bb6258143?w=200&auto=format",
        },
        {
          id: "dish2",
          name: "Saltimbocca",
          imageUrl: "https://images.unsplash.com/photo-1563805042-7684c019e1cb?w=200&auto=format",
        },
        {
          id: "dish3",
          name: "Parmigiana",
          imageUrl: "https://www.giallozafferano.it/images/ricette/201/20149/foto_hd/hd650x433_wm.jpg",
        },
        {
          id: "dish4",
          name: "Fiorentina",
          imageUrl: "https://images.unsplash.com/photo-1558030006-450675393462?w=200&auto=format",
        },
        {
          id: "dish5",
          name: "Orata al forno",
          imageUrl: "https://images.unsplash.com/photo-1519708227418-c8fd9a32b7a2?w=200&auto=format",
        },
        {
          id: "dish6",
          name: "Cotoletta",
          imageUrl: "https://images.unsplash.com/photo-1601050690597-df0568f70950?w=200&auto=format",
        },
      ],
      2: [ // Film
        {
          id: "movie1",
          name: "The Godfather",
          imageUrl: "https://images.unsplash.com/photo-1489599735734-79b4154f88d6?w=200&auto=format",
        },
        {
          id: "movie2",
          name: "Pulp Fiction",
          imageUrl: "https://images.unsplash.com/photo-1440404653325-ab127d49abc1?w=200&auto=format",
        },
        {
          id: "movie3",
          name: "Inception",
          imageUrl: "https://images.unsplash.com/photo-1536440136628-849c177e76a1?w=200&auto=format",
        },
      ],
      3: [ // Videogiochi
        {
          id: "game1",
          name: "The Witcher 3",
          imageUrl: "https://images.unsplash.com/photo-1493711662062-fa541adb3fc8?w=200&auto=format",
        },
        {
          id: "game2",
          name: "Red Dead Redemption 2",
          imageUrl: "https://images.unsplash.com/photo-1542751371-adc38448a05e?w=200&auto=format",
        },
        {
          id: "game3",
          name: "Cyberpunk 2077",
          imageUrl: "https://images.unsplash.com/photo-1552820728-8b83bb6b773f?w=200&auto=format",
        },
      ]
    };

    return fallbackCategories[categoryId] || [];
  };

  // Filtra gli elementi non assegnati a nessun tier
  const unassignedElements = availableElements.filter(
    (element) => !getElementTier(element.id)
  );

  // Gestione stati di loading
  if (categoryLoading || loading) {
    return (
      <div className="element-rooster">
        <h2 className="element-rooster-title">
          {selectedCategory ? selectedCategory.name : "Elementi"}
        </h2>
        <div className="loading-message">Caricamento elementi...</div>
      </div>
    );
  }

  // Se non c'è una categoria selezionata
  if (!selectedCategory) {
    return (
      <div className="element-rooster">
        <h2 className="element-rooster-title">Elementi</h2>
        <div className="no-category-message">
          Seleziona una categoria per visualizzare gli elementi
        </div>
      </div>
    );
  }

  return (
    <div className="element-rooster">
      <h2 className="element-rooster-title">{selectedCategory.name}</h2>
      
      {error && (
        <div className="error-message">
          {error} - Utilizzando dati di esempio
        </div>
      )}
      
      <div className="element-rooster-grid">
        {unassignedElements.map((element) => (
          <div key={element.id} className="dish-card">
            <Element
              id={element.id}
              imageUrl={element.imageUrl}
              showButtons={true}
              showMoveButtons={false}
            />
            <p className="dish-name">{element.name}</p>
          </div>
        ))}
      </div>
      
      {unassignedElements.length === 0 && !loading && availableElements.length > 0 && (
        <div className="rooster-empty">Tutti gli elementi sono stati assegnati</div>
      )}

      {availableElements.length === 0 && !loading && (
        <div className="rooster-empty">Nessun elemento disponibile per questa categoria</div>
      )}
    </div>
  );
};

export default ElementRooster;