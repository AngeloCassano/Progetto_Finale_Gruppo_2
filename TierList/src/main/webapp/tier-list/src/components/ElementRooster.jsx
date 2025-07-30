import React from "react";
import Element from "./Element";
import useTier from "../hooks/useTier";
import "./ElementRooster.css";

const ElementRooster = () => {
  const { getElementTier } = useTier();

  // Dati degli elementi con immagini garantite
  const availableElements = [
    {
      id: "dish1",
      name: "Osso buco",
      imageUrl:
        "https://images.unsplash.com/photo-1603360946369-dc9bb6258143?w=200&auto=format",
    },
    {
      id: "dish2",
      name: "Saltimbocca",
      imageUrl:
        "https://images.unsplash.com/photo-1563805042-7684c019e1cb?w=200&auto=format",
    },
    {
      id: "dish3",
      name: "Parmigiana",
      imageUrl:
        "https://www.giallozafferano.it/images/ricette/201/20149/foto_hd/hd650x433_wm.jpg",
    },
    {
      id: "dish4",
      name: "Fiorentina",
      imageUrl:
        "https://images.unsplash.com/photo-1558030006-450675393462?w=200&auto=format",
    },
    {
      id: "dish5",
      name: "Orata al forno",
      imageUrl:
        "https://images.unsplash.com/photo-1519708227418-c8fd9a32b7a2?w=200&auto=format",
    },
    {
      id: "dish6",
      name: "Cotoletta",
      imageUrl:
        "https://images.unsplash.com/photo-1601050690597-df0568f70950?w=200&auto=format",
    },
  ];

  const unassignedElements = availableElements.filter(
    (element) => !getElementTier(element.id)
  );

  return (
    <div className="element-rooster">
      <h2 className="element-rooster-title">Piatti Italiani</h2>
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
      {unassignedElements.length === 0 && (
        <div className="rooster-empty">Tutti i piatti sono stati assegnati</div>
      )}
    </div>
  );
};

export default ElementRooster;
