export interface Horse {
  id: number;
  name: string;
  description: string;
  birthDay: string;
  sex: string;
  favoriteSportId: number;
  favoriteSportName: string;
  motherId: number;
  motherName: string;
  fatherId: number;
  fatherName: string;
}

export const emptyHorse = (): Horse => ({
  id: null,
  name: null,
  description: null,
  birthDay: null,
  sex: null,
  favoriteSportId: null,
  favoriteSportName: null,
  motherId: null,
  motherName: null,
  fatherId: null,
  fatherName: null,
});

export const createHorse = <T extends Partial<Horse>>(
  initialValues: T
): Horse & T => {
  return Object.assign(emptyHorse(), initialValues);
};
